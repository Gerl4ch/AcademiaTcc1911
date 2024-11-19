package com.example.academiatcc;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class CriarListaDeTreinoActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_criar_lista_de_treino);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Configurando o clique do botão para abrir o fragmento
        FloatingActionButton btnCriarLista = findViewById(R.id.btnCriarLista);  // O ID do botão foi alterado para btnCriarLista
        btnCriarLista.setOnClickListener(v -> abrirFragmentFormulario());
    }

    // Metodo para abrir o fragmento ao clicar no botão

    private void abrirFragmentFormulario() {
        // Criando uma instância do fragmento
        fragmentAbaDeTreinoForm fragment = new fragmentAbaDeTreinoForm();

        // Iniciando a transação de fragmento
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment) // Substitui o conteúdo do FrameLayout pelo fragmento
                .addToBackStack(null) // Para que o botão de voltar funcione corretamente
                .commit();
    }
}