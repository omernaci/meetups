# language: tr
# https://cucumber.io/docs/gherkin/languages/
@Skip
Özellik: Hesap Açılışı
  Senaryo: Geçerli müşteri yeni bir hesap açar
  Diyelim ki Verilen Müşteri ID'si 123
    Ve Müşteri tipi "Bireysel"
    Ve Müşterinin hesap limiti 3'ten azdır
    Ve Müşterinin engellenmiş hesabı yoktur
  O zaman Müşteri "USD" para birimi, "Tahsilat Hesabı" adı, 1000.0 başlangıç bakiyesi, "Vadeli Hesap" türü ve "Aktif" hesap durumu ile yeni bir hesap açar