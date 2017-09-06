/*
 * Autopsy Forensic Browser
 *
 * Copyright 2017 Basis Technology Corp.
 * Contact: carrier <at> sleuthkit <dot> org
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.sleuthkit.autopsy.experimental.autoingest;

import java.util.logging.Level;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.windows.TopComponent;
import org.openide.util.NbBundle.Messages;
import org.openide.windows.Mode;
import org.openide.windows.WindowManager;
import org.sleuthkit.autopsy.coreutils.Logger;

/**
 * Top component which displays the Auto Ingest Dashboard interface.
 */
@ConvertAsProperties(
        dtd = "-//org.sleuthkit.autopsy.experimental.autoingest//AutoIngestDashboard//EN",
        autostore = false
)
@TopComponent.Description(
        preferredID = "AutoIngestDashboardTopComponent",
        //iconBase="SET/PATH/TO/ICON/HERE", 
        persistenceType = TopComponent.PERSISTENCE_NEVER
)
@TopComponent.Registration(mode = "dashboard", openAtStartup = false)
@Messages({
    "CTL_AutoIngestDashboardAction=Auto Ingest Dashboard",
    "CTL_AutoIngestDashboardTopComponent=Auto Ingest Dashboard",
    "HINT_AutoIngestDashboardTopComponent=This is an Auto Ingest Dashboard window"
})
public final class AutoIngestDashboardTopComponent extends TopComponent {
    public final static String PREFERRED_ID = "AutoIngestDashboardTopComponent"; // NON-NLS
    private static final Logger LOGGER = Logger.getLogger(AutoIngestDashboardTopComponent.class.getName());
    private static boolean topComponentInitialized = false;

    public static void openTopComponent() {
        final AutoIngestDashboardTopComponent tc = (AutoIngestDashboardTopComponent) WindowManager.getDefault().findTopComponent(PREFERRED_ID);
        if (tc != null) {
            topComponentInitialized = true;
            WindowManager.getDefault().isTopComponentFloating(tc);
            Mode mode = WindowManager.getDefault().findMode("dashboard"); // NON-NLS
            if (mode != null) {
                mode.dockInto(tc);
            }

            AutoIngestDashboard dashboard = AutoIngestDashboard.getInstance();
            tc.add(dashboard);
            dashboard.setSize(dashboard.getPreferredSize());
            
            tc.open();
            tc.requestActive();
        }
    }

    public static void closeTopComponent() {
        if (topComponentInitialized) {
            final TopComponent etc = WindowManager.getDefault().findTopComponent(PREFERRED_ID);
            if (etc != null) {
                try {
                    etc.close();
                } catch (Exception e) {
                    LOGGER.log(Level.SEVERE, "failed to close " + PREFERRED_ID, e); // NON-NLS
                }
            }
        }
    }

    public AutoIngestDashboardTopComponent() {
        initComponents();
        setName(Bundle.CTL_AutoIngestDashboardTopComponent());
        setToolTipText(Bundle.HINT_AutoIngestDashboardTopComponent());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    @Override
    public void componentOpened() {
        // TODO add custom code on component opening
    }

    @Override
    public void componentClosed() {
        // TODO add custom code on component closing
    }

    void writeProperties(java.util.Properties p) {
        // better to version settings since initial version as advocated at
        // http://wiki.apidesign.org/wiki/PropertyFiles
        p.setProperty("version", "1.0");
        // TODO store your settings
    }

    void readProperties(java.util.Properties p) {
        String version = p.getProperty("version");
        // TODO read your settings according to their version
    }
}