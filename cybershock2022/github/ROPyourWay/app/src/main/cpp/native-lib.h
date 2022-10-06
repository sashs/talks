//
// Created by sash on 25.09.22.
//

#ifndef ROPYOURWAY_NATIVE_LIB_H
#define ROPYOURWAY_NATIVE_LIB_H

struct message {
    int length;
    char message[256];
    void (*do_something)(char*);
};

#endif //ROPYOURWAY_NATIVE_LIB_H
