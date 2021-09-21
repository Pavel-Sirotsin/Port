package com.epam.port.repository;

import java.util.List;
import java.util.Random;

public interface DataGeneratorAble {
   Random RANDOM = new Random();

   <T> List<T> generate();
}
