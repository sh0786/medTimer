package com.lutsh.medtimer.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

public class BluetoothHelper {

    private static final String TAG = "BluetoothHelper";
    private static final UUID MY_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

    private BluetoothAdapter bluetoothAdapter;
    private BluetoothSocket bluetoothSocket;
    private OutputStream outputStream;
    private InputStream inputStream;

    public BluetoothHelper() {
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    }

    // Check if Bluetooth is supported
    public boolean isBluetoothSupported() {
        return bluetoothAdapter != null;
    }

    // Check if Bluetooth is enabled
    public boolean isBluetoothEnabled() {
        return bluetoothAdapter != null && bluetoothAdapter.isEnabled();
    }

    // Connect to a paired device by name
    public boolean connectToDevice(String deviceName) {
        if (bluetoothAdapter == null || !bluetoothAdapter.isEnabled()) {
            Log.e(TAG, "Bluetooth not available or not enabled");
            return false;
        }

        for (BluetoothDevice device : bluetoothAdapter.getBondedDevices()) {
            if (device.getName().equals(deviceName)) {
                try {
                    bluetoothSocket = device.createRfcommSocketToServiceRecord(MY_UUID);
                    bluetoothSocket.connect();
                    outputStream = bluetoothSocket.getOutputStream();
                    inputStream = bluetoothSocket.getInputStream();
                    Log.i(TAG, "Connected to " + deviceName);
                    return true;
                } catch (IOException e) {
                    Log.e(TAG, "Connection failed", e);
                    return false;
                }
            }
        }
        Log.e(TAG, "Device not found: " + deviceName);
        return false;
    }

    // Send data
    public boolean sendData(String message) {
        if (outputStream != null) {
            try {
                outputStream.write(message.getBytes());
                Log.i(TAG, "Data sent: " + message);
                return true;
            } catch (IOException e) {
                Log.e(TAG, "Failed to send data", e);
            }
        }
        return false;
    }

    // Receive data (simple blocking read)
    public String receiveData() {
        if (inputStream != null) {
            byte[] buffer = new byte[1024];
            try {
                int bytes = inputStream.read(buffer);
                return new String(buffer, 0, bytes);
            } catch (IOException e) {
                Log.e(TAG, "Failed to receive data", e);
            }
        }
        return null;
    }

    // Close connection
    public void closeConnection() {
        try {
            if (outputStream != null) outputStream.close();
            if (inputStream != null) inputStream.close();
            if (bluetoothSocket != null) bluetoothSocket.close();
            Log.i(TAG, "Bluetooth connection closed");
        } catch (IOException e) {
            Log.e(TAG, "Error closing connection", e);
        }
    }
}
