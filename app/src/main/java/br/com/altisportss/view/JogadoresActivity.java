package br.com.altisportss.view;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import br.com.altisportss.DTO.JogadorDTO;
import br.com.altisportss.R;
import br.com.altisportss.model.vo.Jogador;
import cz.msebera.android.httpclient.Header;


public class JogadoresActivity extends AppCompatActivity {
    private EditText editId;
    private EditText editNome;
    private EditText editDataNascimento;
    private EditText editPosicao;
    private EditText editCpf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogadores);
        initialize();
    }

    private void initialize() {
        editId = findViewById(R.id.editId);
        editNome = findViewById(R.id.editNome);
        editDataNascimento = findViewById(R.id.editDataNascimento);
        editPosicao = findViewById(R.id.editPosicao);
        editCpf = findViewById(R.id.editCpf);
    }

    public void pesquisar(View v) {
        String id = editId.getText().toString();

        AsyncHttpClient client = new AsyncHttpClient();
        client.get("http://192.168.15.42:80/ler.php?id=" + id, new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                Toast.makeText(JogadoresActivity.this, "Iniciando...", Toast.LENGTH_SHORT).show();
                editNome.setText("Aguardando");
            }

            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                String s = new String(bytes);

                Gson gson = new Gson();

                JogadorDTO jogadorDTO = gson.fromJson(s, JogadorDTO.class);
                Jogador jogador = jogadorDTO.getJogador();

                //Algoritmo pós execução da requisição
                popoularForm(jogador);
            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                Toast.makeText(JogadoresActivity.this, "Falha", Toast.LENGTH_SHORT).show();
                editNome.setText("");

            }
        });
    }
    public void popoularForm(Jogador jogador){
        editNome.setText(jogador.getNome());
        editDataNascimento.setText(jogador.getDataNascimento());
        editPosicao.setText(jogador.getPosicao());
        editCpf.setText(jogador.getCpf());
    }
}