package br.com.altisportss.viaCep;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import br.com.altisportss.R;
import cz.msebera.android.httpclient.Header;


public class CepActivity extends AppCompatActivity {

    private EditText editCep;
    private EditText editEstado;
    private EditText editCidade;
    private EditText editBairro;
    private EditText editRua;
    private EditText editSucesso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cep);
        initialize();
    }

    private void initialize(){
        editCep = findViewById(R.id.editCep);
        editEstado = findViewById(R.id.editEstado);
        editCidade = findViewById(R.id.editCidade);
        editBairro = findViewById(R.id.editBairro);
        editRua = findViewById(R.id.editRua);
        editSucesso = findViewById(R.id.editSucesso);
    }

    public void pesquisar(View v){
        String cep = editCep.getText().toString();

        AsyncHttpClient client = new AsyncHttpClient();
        client.get("https://viacep.com.br/ws/" + cep + "/json", new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                Toast.makeText(CepActivity.this,"Iniciando", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                String s = new String(bytes);

                Gson gson = new Gson();

                EnderecoDTO enderecoDTO = gson.fromJson(s, EnderecoDTO.class);
                Endereco endereco = enderecoDTO.getEndereco();

                String uf = endereco.getEstado();
                String sc = "SC";
                if(uf.equals(sc)){
                    editSucesso.setText("Cep encontrado com sucesso!");
                    popoularForm(endereco);
                } else { popoularForm(endereco);
                    editSucesso.setText("!!!  Não entregamos no seu estado !!!");

                }

            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                Toast.makeText(CepActivity.this, "Falha", Toast.LENGTH_SHORT).show();
                editEstado.setText("");
            }
        });

    }

    public void popoularForm(Endereco endereco){
        editEstado.setText(endereco.getEstado());
        editCidade.setText(endereco.getCidade());
        editBairro.setText(endereco.getBairro());
        editRua.setText(endereco.getRua());
    }
}
