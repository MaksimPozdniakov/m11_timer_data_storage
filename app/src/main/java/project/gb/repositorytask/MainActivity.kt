package project.gb.repositorytask

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import project.gb.repositorytask.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var _binding : ActivityMainBinding? = null
    private val binding get() = _binding!!

    private val repository = Repository(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        listenerButtons()

        // Первичный вывод записанного значения
        val textFromRepository = repository.getText()
        binding.textView.text = textFromRepository
    }

    private fun listenerButtons() {
        binding.buttonSave.setOnClickListener {
            repository.saveText(binding.editText.text.toString())

            val textFromRepository = repository.getText()
            binding.textView.text = textFromRepository

            binding.editText.text.clear()
        }

        binding.buttonClear.setOnClickListener {
            repository.clearText()
            Toast.makeText(this, "Репозиторий очищен", Toast.LENGTH_SHORT).show()
        }
    }

}