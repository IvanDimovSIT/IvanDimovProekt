package com.company;

import java.io.FileNotFoundException;

public interface OpenFile {
    void open(Schedule schedule ,String fileName)throws FileNotFoundException;
}
