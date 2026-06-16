# SmileCenterWelcome — Android TV Hasta Karşılama Uygulaması

Klinik bekleme salonundaki televizyona kurulmak üzere geliştirilmiş Android uygulaması.

## Ekranlar

| Ekran | Açıklama |
|-------|----------|
| **Welcome** | "Welcome Barış" hoş geldiniz mesajı — 5 saniye sonra otomatik geçiş |
| **Video** | YouTube videosu tam ekran oynar, bitince kendiliğinden döngüye girer |

---

## APK Derleme (Android Studio — Önerilen)

### Gereksinimler
- [Android Studio](https://developer.android.com/studio) (son sürüm)
- JDK 11 veya 17 (Android Studio ile birlikte gelir)
- İnternet bağlantısı (Gradle bağımlılıkları için)

### Adımlar

1. **Projeyi aç:**  
   `File → Open → SmileCenterWelcome klasörünü seç → OK`

2. **Gradle sync bekle** (ilk açılışta biraz sürebilir)

3. **Debug APK derle:**  
   `Build → Build Bundle(s) / APK(s) → Build APK(s)`

4. **APK konumu:**  
   `app/build/outputs/apk/debug/app-debug.apk`

---

## Televizyona Kurulum

### Yöntem 1: ADB (Wi-Fi üzerinden)

1. **TV'de geliştirici modunu aç:**  
   `Ayarlar → Cihaz Tercihleri → Hakkında → Derleme → 7 kez tıkla`

2. **ADB debuglamayı etkinleştir:**  
   `Ayarlar → Cihaz Tercihleri → Geliştirici Seçenekleri → USB hata ayıklama: AÇIK`

3. **TV'nin IP adresini bul:**  
   `Ayarlar → Cihaz Tercihleri → Hakkında → Durum → IP Adresi`

4. **Terminalde çalıştır:**
   ```bash
   adb connect <TV_IP>:5555
   adb install app-debug.apk
   ```

### Yöntem 2: USB (uyumlu TV'lerde)
- APK'yı USB belleğe kopyala
- TV'nin Dosya Yöneticisi uygulamasından APK'yı aç ve yükle

---

## Özelleştirme

### İsmi değiştir
`app/src/main/res/layout/activity_main.xml` → `android:text="Barış"` satırını bul

### Video değiştir
`app/src/main/java/com/sct/smilecenterwelcome/VideoActivity.kt`
```kotlin
private val VIDEO_ID = "-dIvdJX8O8o"  // ← YouTube video ID'sini değiştir
```

### Welcome süresi değiştir
`MainActivity.kt` → `CountDownTimer(6000, 1000)` → `6000` = 6 saniye (ms cinsinden)

---

## GitHub'a Push

```bash
git init
git add .
git commit -m "Initial: Smile Center Welcome TV App"
git branch -M main
git remote add origin https://github.com/KULLANICI_ADI/SmileCenterWelcome.git
git push -u origin main
```
