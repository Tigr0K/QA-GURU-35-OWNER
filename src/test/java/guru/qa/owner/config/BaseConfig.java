package guru.qa.owner.config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:${runType}.properties",
        "classpath:local.properties",
})

public interface BaseConfig extends Config {
    @Key("browserName")
    @DefaultValue("chrome")
    String browserName();

    @Key("browserVersion")
    @DefaultValue("113")
    String browserVersion();

    @Key("isRemote")
    @DefaultValue("false")
    boolean isRemote();

    @Key("remoteUrl")
    @DefaultValue("https://www.wildberries.ru")
    String remoteUrl();
}
