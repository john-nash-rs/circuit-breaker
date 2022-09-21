package com.company;
public interface IExternalCall<T, K> {
    T run(K request);
}