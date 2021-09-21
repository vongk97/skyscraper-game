package org.csc133.a2;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.table.TableLayout;

/**
 * organizes all numerical displays
 */
public class GlassCockpit extends Container{
    private GameWorld gwModel;
    private GameClockComponent gclockc;

    /**
     * @param gwModel
     */
    public GlassCockpit(GameWorld gwModel) {
        this.gwModel = gwModel;
        // set glass cockpit display layout
        this.setLayout(new BorderLayout());
        this.getAllStyles().setBorder(Border.createLineBorder(4, ColorUtil.GREEN));

        Container cockpit = new Container(new TableLayout(2, 6));
        cockpit.getAllStyles().setBorder(Border.createLineBorder(2, ColorUtil.BLACK));
        cockpit.getAllStyles().setBgTransparency(120);
        cockpit.getAllStyles().setBgColor(ColorUtil.GRAY);
        //cockpit.getAllStyles().setMargin(10, 10, 10, 10);
        cockpit.add(newClockLabel(0, 0, "GAME TIME")).
                add(newClockLabel(0, 0, "FUEL")).
                add(newClockLabel(0, 0, "DAMAGE")).
                add(newClockLabel(0, 0, "LIVES")).
                add(newClockLabel(0, 0, "LAST")).
                add(newClockLabel(0, 0, "HEADING")).
                add(new GameClockComponent()).
                add(new FuelDisplayComponent(gwModel)).
                add(new DamageDisplayComponent(gwModel)).
                add(new LivesDisplayComponent(gwModel)).
                add(new LastDisplayComponent(gwModel)).
                add(new HeadingDisplayComponent(gwModel));
        // Place the cockpit in north region
        this.add(BorderLayout.NORTH, cockpit);
    }

    Label newClockLabel(int m, int p, String str) {
        Label aLabel = new Label(str);
        aLabel.getAllStyles().setPadding(p, p, p, p);
        aLabel.getAllStyles().setMargin(m, m, m, m);
        aLabel.setAutoSizeMode(true);
        aLabel.setMaxAutoSize(2);
        return aLabel;
    }
}
