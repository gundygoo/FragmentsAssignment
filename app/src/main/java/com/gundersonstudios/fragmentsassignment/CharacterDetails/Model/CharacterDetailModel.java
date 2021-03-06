package com.gundersonstudios.fragmentsassignment.CharacterDetails.Model;

/**
 * Created by Erik on 2/6/2017.
 */

public class CharacterDetailModel {
    private String m_CharacterName;
    private String m_Quote;
    private String m_MediaName;
    private String m_Description;

    private String m_ResourceString;

    public CharacterDetailModel(String characterName, String quote, String mediaName, String description, String resourceString) {
        m_CharacterName = characterName;
        m_Quote = quote;
        m_MediaName = mediaName;
        m_Description = description;
        m_ResourceString = resourceString;
    }

    public String getCharacterName() {
        return m_CharacterName;
    }

    public String getQuote() {
        return m_Quote;
    }

    public String getMediaName() {
        return m_MediaName;
    }

    public String getDescription() {
        return m_Description;
    }

    public String getResourceString() {
        return m_ResourceString;
    }
}
