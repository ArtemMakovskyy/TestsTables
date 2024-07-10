package com.winestoreapp.multithreading.other;

import lombok.Data;

public class SyncronizeConstructor {
    public static void main(String[] args) {
        SyncClass sc = new SyncClass(1, 2);
    }
}

@Data
class SyncClass {
    private int volume1;
    private int volume2;

    public SyncClass(int volume1, int volume2) {
        synchronized (this) {
            this.volume1 = volume1;
            this.volume2 = volume2;
        }
        {
            synchronized (this) {
                System.out.println("v1 - " + volume1 + ", v2 - " + volume2);
                System.out.println(" **Доцільність використання**: Синхронізація конструктора може бути важливою для забезпечення безпеки даних і уникнення гонок при ініціалізації об'єктів, особливо у випадках з багатопотоковим середовищем.");
            }
        }
    }
}