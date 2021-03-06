package com.talan.testflow.core.helper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Couple<T,V> {

    private T t;
    private V v;
}
