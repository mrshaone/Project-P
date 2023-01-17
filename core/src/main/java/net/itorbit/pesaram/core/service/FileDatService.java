package net.itorbit.pesaram.core.service;

import org.springframework.stereotype.Service;

import java.io.File;

@Service
public interface FileDatService {
    void saveData(File file);
}
