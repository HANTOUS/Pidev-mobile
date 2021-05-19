

package com.pi.tevent.Gui;

import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Component;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.layouts.Layout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;

/**
 * Base class for the forms with common functionality
 *
 * @author Shai Almog
 */
public class BaseForm extends Form {

    //Form previous;
    public BaseForm() {
                    //  this.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, evt -> previous.showBack());

    }

    public BaseForm(Layout contentPaneLayout) {
        super(contentPaneLayout);
              //this.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, evt -> previous.showBack());

    }

    public BaseForm(String title, Layout contentPaneLayout ) {
        super(title, contentPaneLayout);
     // this.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, evt -> previous.showBack());

    }
    
    
    public Component createLineSeparator() {
        Label separator = new Label("", "WhiteSeparator");
        separator.setShowEvenIfBlank(true);
        return separator;
    }

    public Component createDLineSeparator() {
        Label separator = new Label("", "WhiteSeparator1");
        separator.setShowEvenIfBlank(true);
        return separator;
    }
    
    public Component createLineSeparator(int color) {
        Label separator = new Label("", "WhiteSeparator");
        separator.getUnselectedStyle().setBgColor(color);
        separator.getUnselectedStyle().setBgTransparency(255);
        separator.setShowEvenIfBlank(true);
        return separator;
    }

    protected void addSideMenu(Resources res) {
        Toolbar tb = getToolbar();
        Image img = res.getImage("about1.jpg");
        if(img.getHeight() > Display.getInstance().getDisplayHeight() / 3) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 3);
        }
        ScaleImageLabel sl = new ScaleImageLabel(img);
        sl.setUIID("BottomPad");
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        
        tb.addComponentToSideMenu(LayeredLayout.encloseIn(
                sl,
                FlowLayout.encloseCenterBottom(
                        new Label(res.getImage("pdp.jpg"), "PictureWhiteBackground"))
        ));
        
        tb.addMaterialCommandToSideMenu("Camping et randonnées", FontImage.MATERIAL_UPDATE, e -> new HomeFormEvent().show());
        //tb.addMaterialCommandToSideMenu("Festival", FontImage.MATERIAL_UPDATE, e -> new LoginForm(res).show());
        //tb.addMaterialCommandToSideMenu("Randonné", FontImage.MATERIAL_UPDATE, e -> new LoginForm(res).show());
        tb.addMaterialCommandToSideMenu("Bus", FontImage.MATERIAL_UPDATE, e -> new BusGui("Nos Bus",res).show());
        tb.addMaterialCommandToSideMenu("Materiels", FontImage.MATERIAL_UPDATE, e -> new MaterielGui("Nos Materiels",res).show());
        tb.addMaterialCommandToSideMenu("Demandes", FontImage.MATERIAL_UPDATE, e -> new DemandesForm(res).show());
        tb.addMaterialCommandToSideMenu("Reclamation", FontImage.MATERIAL_UPDATE, e -> new HomeReclamationForm(res).show());
        tb.addMaterialCommandToSideMenu("Profile", FontImage.MATERIAL_SETTINGS, e -> new ProfilForm(res).show());
        tb.addMaterialCommandToSideMenu("Confidentialité", FontImage.MATERIAL_SETTINGS, e -> new ResetForm(res).show());
        tb.addMaterialCommandToSideMenu("Logout", FontImage.MATERIAL_EXIT_TO_APP, e -> new LoginForm(res).show());
    }
}
