package reflectdemo;

/**
 * @author 90774
 */
public enum TestEnum {
    SMALL("S"),MEDIUM("M"),LARGE("L"),EXTRA_LARGE("XL");

    private String abbreviation;

    private TestEnum(String abbreviation)
    {
        this.abbreviation = abbreviation;
    }

    public String getAbbreviation(){return abbreviation;}
}
zs