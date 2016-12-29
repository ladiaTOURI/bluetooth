package bluetooth.android.mbed.com.bluetooth;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Set;

public class MainActivity extends Activity  {
    Button b1,b2,b3,b4;
    private BluetoothAdapter BA;
    private Set<BluetoothDevice>pairedDevices;
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b1 = (Button) findViewById(R.id.button);
        b2=(Button)findViewById(R.id.button2);
        b3=(Button)findViewById(R.id.button3);
        b4=(Button)findViewById(R.id.button4);
// classe BluetoothAdapter nous permetrra  de savoir si l'appareil possede la technologie bleutooth
        //grace  a la methode getDefaultAdapter
        BA = BluetoothAdapter.getDefaultAdapter();
        lv = (ListView)findViewById(R.id.listView);
    }
//activer bluetooth avec la methode startActivityForResult avec l'intent l'ACTION_REQUEST_ENABLE
    //  dans  ce  cas on demande à l'utilisateur  de l'activer, on l'active pas directement
    //on peut verifier s'il est  ectiver avec la methode isEnabled
    public void on(View v){
        if (!BA.isEnabled()) {
            Intent turnOn = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(turnOn, 0);
            Toast.makeText(getApplicationContext(), "Turned on",Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), "Already on", Toast.LENGTH_LONG).show();
        }
    }
// methode pour désactiver le  bluettooth avec disable
    public void off(View v){
        BA.disable();
        Toast.makeText(getApplicationContext(), "Turned off" ,Toast.LENGTH_LONG).show();
    }
    // permettre a  d'autres appareil de  se  connecter avec la méthode   startActivityForResult
    //  startActivityForResult  possede comme valeur ACTION_REQUEST_DISCOVERABLE
    public  void visible(View v){
        Intent getVisible = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        startActivityForResult(getVisible, 0);
    }

//  découvrir les différents  appreils  connecter  a  note  apppareil  cible  avec la methode getBondedDevices
    public void list(View v){
        pairedDevices = BA.getBondedDevices();

        ArrayList list = new ArrayList();
        //dans ce cas on veux  parcourir la list la  list des appareils trouvés

        for(BluetoothDevice bt : pairedDevices) list.add(bt.getName());
        Toast.makeText(getApplicationContext(), "Showing Paired Devices",Toast.LENGTH_SHORT).show();

        final ArrayAdapter adapter = new  ArrayAdapter(this,android.R.layout.simple_list_item_1, list);

        lv.setAdapter(adapter);
    }
}
