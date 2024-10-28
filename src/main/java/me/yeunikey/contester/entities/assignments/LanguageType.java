package me.yeunikey.contester.entities.assignments;

public enum LanguageType {

    cpp,
    python;

    public static LanguageType getLanguage(String language) {
        for (LanguageType type : LanguageType.values()) {
            if (type.toString().equalsIgnoreCase(language)) {
                return type;
            }
        }
        return null;
    }

}
