package com.lutsh.medtimer.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.widget.Toast;

public class BluetoothHelper {
    private BluetoothAdapter bluetoothAdapter;

    // Constructor
    public BluetoothHelper(Context context) {
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (bluetoothAdapter == null) {
            Toast.makeText(context, "Bluetooth not supported", Toast.LENGTH_SHORT).show();
        }
 
   }

    // Check if Bluetooth is enabled
    public boolean isBluetoothEnabled() {
        return bluetoothAdapter != null && bluetoothAdapter.isEnabled();
    }

    // Enable Bluetooth
    public void enableBluetooth() {
        if (bluetoothAdapter != null && !bluetoothAdapter.isEnabled()) {
            bluetoothAdapter.enable();
        }
    }

    // Disable Bluetooth
    public void disableBluetooth() {
        if (bluetoothAdapter != null && bluetoothAdapter.isEnabled()) {
            bluetoothAdapter.disable();
        }
    }
}
