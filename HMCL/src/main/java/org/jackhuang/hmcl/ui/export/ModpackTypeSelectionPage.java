/*
 * Hello Minecraft! Launcher
 * Copyright (C) 2020  huangyuhui <huanghongxun2008@126.com> and contributors
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package org.jackhuang.hmcl.ui.export;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;
import org.jackhuang.hmcl.mod.ModpackExportInfo;
import org.jackhuang.hmcl.mod.mcbbs.McbbsModpackExportTask;
import org.jackhuang.hmcl.mod.multimc.MultiMCModpackExportTask;
import org.jackhuang.hmcl.mod.server.ServerModpackExportTask;
import org.jackhuang.hmcl.ui.FXUtils;
import org.jackhuang.hmcl.ui.wizard.WizardController;
import org.jackhuang.hmcl.ui.wizard.WizardPage;

import java.util.Map;

import static org.jackhuang.hmcl.ui.export.ModpackInfoPage.MODPACK_INFO_OPTION;
import static org.jackhuang.hmcl.util.i18n.I18n.i18n;

public final class ModpackTypeSelectionPage extends StackPane implements WizardPage {
    private final WizardController controller;
    @FXML
    private JFXButton btnMCBBS;
    @FXML
    private JFXButton btnMultiMC;
    @FXML
    private JFXButton btnServer;

    public ModpackTypeSelectionPage(WizardController controller) {
        this.controller = controller;
        FXUtils.loadFXML(this, "/assets/fxml/modpack/type.fxml");

        JFXButton[] buttons = new JFXButton[]{btnMCBBS, btnMultiMC, btnServer};
        String[] types = new String[]{MODPACK_TYPE_MCBBS, MODPACK_TYPE_MULTIMC, MODPACK_TYPE_SERVER};
        ModpackExportInfo.Options[] options = new ModpackExportInfo.Options[]{McbbsModpackExportTask.OPTION, MultiMCModpackExportTask.OPTION, ServerModpackExportTask.OPTION};
        for (int i = 0; i < types.length; ++i) {
            String type = types[i];
            ModpackExportInfo.Options option = options[i];
            buttons[i].setOnMouseClicked(e -> {
                controller.getSettings().put(MODPACK_TYPE, type);
                controller.getSettings().put(MODPACK_INFO_OPTION, option);
                controller.onNext();
            });
        }
    }

    @Override
    public void cleanup(Map<String, Object> settings) {
    }

    @Override
    public String getTitle() {
        return i18n("modpack.wizard.step.3.title");
    }

    public static final String MODPACK_TYPE = "modpack.type";

    public static final String MODPACK_TYPE_MCBBS = "mcbbs";
    public static final String MODPACK_TYPE_MULTIMC = "multimc";
    public static final String MODPACK_TYPE_SERVER = "server";
}
