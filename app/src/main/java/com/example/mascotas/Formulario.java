package com.example.mascotas;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Formulario extends AppCompatActivity {

    EditText etNombre;
    EditText etMail;
    EditText etMensaje;
    Button btnEnviar;
    String sEmail, sPassword;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        etNombre = (EditText) findViewById(R.id.etNombre);
        etMail = (EditText) findViewById(R.id.etMail);
        etMensaje = (EditText) findViewById(R.id.etMensaje);
        btnEnviar = (Button) findViewById(R.id.btnEnviar);

        //Credenciales de mail
        sEmail = "coursera.pelaezbrian@gmail.com";
        sPassword = "prueba123";

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // INICIALIZAR PROPIEDADES
                Properties propiedades = new Properties();
                propiedades.put("mail.smtp.auth","true");
                propiedades.put("mail.smtp.starttls.enable","true");
                propiedades.put("mail.smtp.host","smtp.gmail.com");
                propiedades.put("mail.smtp.port","587");

                //INICIALIZAR SESION
                Session session = Session.getInstance(propiedades, new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(sEmail,sPassword);
                    }
                });


                try{
                    // Inicializar contenido de email
                    Message mensaje = new MimeMessage(session);
                    // Setear mailde origen
                    mensaje.setFrom(new InternetAddress(sEmail));
                    // Setear mail receptor
                    mensaje.setRecipients(Message.RecipientType.TO, InternetAddress.parse(etMail.getText().toString().trim()));
                    // Setear asunto
                    //mensaje.setSubject("Mascota");
                    // Setear mensaje
                    mensaje.setText(etMensaje.getText().toString().trim());

                    // Enviar mail
                    new SendMail().execute(mensaje);
                } catch (MessagingException e){
                    e.printStackTrace();
                }
            }
        });
    }


    private class SendMail extends AsyncTask<Message, String, String> {

        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = ProgressDialog.show(Formulario.this,"Espere por favor...","Enviando...", true, false);
        }

        @Override
        protected String doInBackground(Message... messages) {
            try {
                Transport.send(messages[0]);
                return "Hecho!";
            } catch (MessagingException e) {
                e.printStackTrace();
                return "Error";
            }

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progressDialog.dismiss();
            if (s.equals("Hecho!")){
                AlertDialog.Builder builder = new AlertDialog.Builder(Formulario.this);
                builder.setCancelable(false);
                builder.setTitle(Html.fromHtml("<font color='#509324'>Sucess</font>"));
                builder.setMessage("Mensaje enviado correctamente.");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        // Limpiar todos los edit
                        etMail.setText("");
                        etMensaje.setText("");
                        etNombre.setText("");
                    }
                });
                // Muestra alerta de dialogo
                builder.show();
            } else{
                //Cuando falla
                Toast.makeText(getApplicationContext(), "Algo salio mal",Toast.LENGTH_LONG).show();
            }
        }
    }
}
