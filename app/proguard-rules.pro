# Add project specific ProGuard rules here.
-keep class * extends androidx.room.RoomDatabase
-keep @androidx.room.Entity class *
-dontwarn javax.annotation.**
