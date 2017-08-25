package de.obdachioser.capturethebay.config;

import lombok.Getter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.yaml.snakeyaml.Yaml;

import java.io.File;

/**
 * Created by ObdachIoser at 15:18 on 25.08.2017.
 *
 * @TODO
 * @Finished: false
 */

@Getter
public class LocaleGameConfiguration {

    private FileConfiguration fileConfiguration;
    private File file;

    private boolean applied = false;

    public void apply(File file) {

        this.file = file;

        try {

            if(!file.exists()) {

                file.mkdir();
                file.mkdir();

                file.createNewFile();
            }

            this.fileConfiguration = YamlConfiguration.loadConfiguration(file);

        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    public void save() {

        if(!isApplied()) return;

        try {

            this.fileConfiguration.save(file);
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }
}
