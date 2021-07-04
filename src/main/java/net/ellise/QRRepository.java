package net.ellise;

import java.io.File;

public interface QRRepository {
    File create(Action action, int id);
    File find(Action action, int id);
    void remove(Action action, int id);
}
