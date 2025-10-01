if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
    if (checkSelfPermission(Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
        requestPermissions(new String[]{Manifest.permission.BLUETOOTH_CONNECT}, 1);
    }
}
