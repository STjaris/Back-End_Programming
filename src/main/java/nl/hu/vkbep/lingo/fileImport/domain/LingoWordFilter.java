package nl.hu.vkbep.lingo.fileImport.domain;

public class LingoWordFilter implements WordFilter {

    @Override
    public boolean stringFiltering(String input) {
        return input.matches("^[a-z]{5,7}$");
    }
}
