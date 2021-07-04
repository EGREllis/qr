package net.ellise;

import java.io.File;

public class QRRepositoryImpl implements QRRepository {
    private final QRConverter converter;
    private final String repositoryBase;
    private final String urlBase;

    public QRRepositoryImpl(QRConverter converter, String repositoryBase, String urlBase) {
        this.converter = converter;
        this.repositoryBase = repositoryBase;
        this.urlBase = urlBase;
    }

    @Override
    public File create(Action action, int id) {
        String url = action.getUrl(urlBase, id);
        File file = action.getFile(repositoryBase, id);
        return converter.convertFromStringToFile(file, url);
    }

    @Override
    public File find(Action action, int id) {
        return action.getFile(repositoryBase, id);
    }

    @Override
    public void remove(Action action, int id) {
        File file = action.getFile(repositoryBase, id);
        file.delete();
    }
}
