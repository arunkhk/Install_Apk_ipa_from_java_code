adb install "yaantra.qc.apk"
adb uninstall com.yaantra.buyback.app
adb install "yaantra.qc.apk" 
adb shell am start -n com.yaantra.buyback.app/com.example.hp.yaantrabuyback.MainActivity
