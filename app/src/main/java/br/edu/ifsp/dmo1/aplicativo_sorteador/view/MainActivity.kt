package br.edu.ifsp.dmo1.aplicativo_sorteador.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.dmo1.aplicativo_sorteador.R
import br.edu.ifsp.dmo1.aplicativo_sorteador.databinding.ActivityMainBinding
import br.edu.ifsp.dmo1.aplicativo_sorteador.model.Draw

class MainActivity : AppCompatActivity(), OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private var draw = Draw()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setClickListener()
    }

    override fun onClick(v: View) {
        when(v) {
            binding.buttonUseLimit -> {
                val limit: Int = try {
                    binding.editLimit.text.toString().toInt()
                } catch (e: NumberFormatException) {
                    -1
                }
                draw = if(limit > 1)
                    Draw(limit)
                else
                    Draw()
                updateUI()
            }
            binding.buttonDraw -> {
                val number = draw.getNumber()
                updateDrawNumberDisplay(number)
                updateListView()
            }
        }
    }

    private fun updateListView() {
        val adapter: ArrayAdapter<String> = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            draw.getHistory(this)
        )
        binding.listviewDraw.adapter = adapter
    }

    @SuppressLint("DefaultLocale")
    private fun updateUI() {
        val str = getString(R.string.intervalo, draw.getHighBorder())
        binding.textviewInterval.text = str
        binding.editLimit.text.clear()
        binding.textviewExit.text = getString(R.string.inicie_o_sorteio)
        updateListView()
    }

    private fun setClickListener() {
        binding.buttonDraw.setOnClickListener(this)
        binding.buttonUseLimit.setOnClickListener(this)
    }

    private fun updateDrawNumberDisplay(number: Int) {
        if(number >= 0) {
            binding.textviewExit.text = number.toString()
        } else {
            binding.textviewExit.text = getString(R.string.limite_atingido)
            Toast.makeText(this, R.string.toast_maximo_sorteio, Toast.LENGTH_SHORT).show()
        }
    }
}