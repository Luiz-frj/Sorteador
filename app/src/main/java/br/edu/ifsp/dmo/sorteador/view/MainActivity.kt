package br.edu.ifsp.dmo.sorteador.view

import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.dmo.sorteador.R
import br.edu.ifsp.dmo.sorteador.databinding.ActivityMainBinding
import br.edu.ifsp.dmo.sorteador.model.Draw

class MainActivity : AppCompatActivity(), OnClickListener {
    private lateinit var binding: ActivityMainBinding;
    private var draw = Draw();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater);
        setContentView(binding.root);

        setClickListener();
    }

    private fun setClickListener() {
        TODO("Not yet implemented")
    }

    override fun onClick(v: View) {
        when(v){
            binding.buttonUseLimit -> {
                val limit: Int = try{
                    binding.editLimit.text.toString().toInt();
                } catch (e: NumberFormatException){
                    -1;
                }
                draw = if(limit > 1){
                    Draw(limit);
                } else{
                    Draw();
                }
                updateUI();
            }

            binding.buttonDraw -> {
                binding.textviewExit.text = draw.getNumber().toString();
                updateListview();
            }
        }
    }

    private fun setClicListener(){
        binding.buttonDraw.setOnClickListener(this);
        binding.buttonUseLimit.setOnClickListener(this);
    }

    private fun updateUI(){
        val str = String.format("Intervalo de 1 à %,d.", draw.getHighBorder());
        binding.textviewInterval.text = str;
        binding.editLimit.text.clear();
        binding.textviewExit.text = getString(R.string.inicie_o_sorteio);
        updateListview();
    }

    private fun updateListview() {
        val adapter: ArrayAdapter<Int> = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            draw.getHistory()
        )
        binding.listviewDraw.adapter = adapter;
    }

}