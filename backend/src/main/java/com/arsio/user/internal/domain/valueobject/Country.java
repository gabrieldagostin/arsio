package com.arsio.user.internal.domain.valueobject;

public enum Country {

    AF("AF", "Afghanistan", "🇦🇫"),
    AL("AL", "Albania", "🇦🇱"),
    AR("AR", "Argentina", "🇦🇷"),
    AU("AU", "Australia", "🇦🇺"),
    AT("AT", "Austria", "🇦🇹"),
    BE("BE", "Belgium", "🇧🇪"),
    BR("BR", "Brazil", "🇧🇷"),
    CA("CA", "Canada", "🇨🇦"),
    CL("CL", "Chile", "🇨🇱"),
    CN("CN", "China", "🇨🇳"),
    CO("CO", "Colombia", "🇨🇴"),
    HR("HR", "Croatia", "🇭🇷"),
    CZ("CZ", "Czech Republic", "🇨🇿"),
    DK("DK", "Denmark", "🇩🇰"),
    EG("EG", "Egypt", "🇪🇬"),
    FI("FI", "Finland", "🇫🇮"),
    FR("FR", "France", "🇫🇷"),
    DE("DE", "Germany", "🇩🇪"),
    GR("GR", "Greece", "🇬🇷"),
    HU("HU", "Hungary", "🇭🇺"),
    IN("IN", "India", "🇮🇳"),
    ID("ID", "Indonesia", "🇮🇩"),
    IE("IE", "Ireland", "🇮🇪"),
    IT("IT", "Italy", "🇮🇹"),
    JP("JP", "Japan", "🇯🇵"),
    KR("KR", "South Korea", "🇰🇷"),
    MX("MX", "Mexico", "🇲🇽"),
    NL("NL", "Netherlands", "🇳🇱"),
    NZ("NZ", "New Zealand", "🇳🇿"),
    NO("NO", "Norway", "🇳🇴"),
    PE("PE", "Peru", "🇵🇪"),
    PL("PL", "Poland", "🇵🇱"),
    PT("PT", "Portugal", "🇵🇹"),
    RO("RO", "Romania", "🇷🇴"),
    RU("RU", "Russia", "🇷🇺"),
    ES("ES", "Spain", "🇪🇸"),
    SE("SE", "Sweden", "🇸🇪"),
    CH("CH", "Switzerland", "🇨🇭"),
    TR("TR", "Turkey", "🇹🇷"),
    UA("UA", "Ukraine", "🇺🇦"),
    GB("GB", "United Kingdom", "🇬🇧"),
    US("US", "United States", "🇺🇸"),
    UY("UY", "Uruguay", "🇺🇾"),
    VE("VE", "Venezuela", "🇻🇪"),
    VN("VN", "Vietnam", "🇻🇳");

    private final String code;
    private final String name;
    private final String flag;

    Country(String code, String name, String flag) {
        this.code = code;
        this.name = name;
        this.flag = flag;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getFlag() {
        return flag;
    }
}
