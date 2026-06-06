# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# defaultConfig {} block in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

# Hilt
-keep class com.google.dagger.hilt.** { *; }
-keepclasseswithmembers class * {
    @com.google.dagger.hilt.* <fields>;
}

# Kotlin Coroutines
-keepclassmembernames class kotlinx.coroutines.internal.MainDispatcherFactory {
    volatile static kotlinx.coroutines.MainDispatcher instance;
}
-keepclassmembernames class kotlinx.coroutines.Dispatchers {
    static final kotlinx.coroutines.MainDispatcher main;
}
