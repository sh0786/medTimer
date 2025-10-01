import com.lutsh.medtimer.bluetooth.BluetoothHelper;

public class MainActivity extends AppCompatActivity {
    private BluetoothHelper bluetoothHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // BluetoothHelper initialize
        bluetoothHelper = new BluetoothHelper(this);

        // Example: Enable Bluetooth if it’s off
        if (!bluetoothHelper.isBluetoothEnabled()) {
            bluetoothHelper.enableBluetooth();
        }
    }
}
