#include <jni.h>
#include <string>
#include "native-lib.h"

extern "C" JNIEXPORT jstring JNICALL
Java_de_scoding_ropyourway_MainActivity_stringFromJNI(
        JNIEnv* env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}
extern "C"
JNIEXPORT jstring JNICALL
Java_de_scoding_ropyourway_MainActivity_parseMessage(
        JNIEnv* env,
        jobject /* this */,
        jbyteArray message, jint length){

    jboolean isCopy;
    char buffer[32];
    struct message* m = (struct message *)malloc(sizeof (struct message));
    jstring string = env->NewString(reinterpret_cast<const jchar *>(""), 5);

    char * nativeString = (char*)env->GetByteArrayElements( message, &isCopy);
    memcpy(m->message, nativeString, length);
    memcpy(buffer, nativeString, length);
return string;
}