package com.pi.tevent;


import com.codename1.messaging.Message;
import static com.codename1.ui.CN.*;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Dialog;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.ui.Toolbar;
import com.pi.tevent.Gui.ChartForm;
import com.pi.tevent.Gui.DemandesForm;
import com.pi.tevent.Services.DemandeBusServices;
import java.io.IOException;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.io.NetworkEvent;
import com.pi.tevent.Gui.AddDemandeBusForm;
import com.pi.tevent.Gui.AddDemandeChauffeurForm;
import com.pi.tevent.Gui.AddDemandeMaterielForm;
import com.pi.tevent.Gui.ListDemandeBusAdminForm;
import com.pi.tevent.Gui.ListDemandeChauffeurAdminForm;
import com.pi.tevent.Gui.ListDemandeMaterielAdminForm;
import com.pi.tevent.Gui.ListDemandeMaterielForm;
import com.pi.tevent.Gui.AddBusFrom;
import com.pi.tevent.Gui.AddMaterielGui;
import com.pi.tevent.Gui.BusAGui;
import com.pi.tevent.Gui.BusGui;
import com.pi.tevent.Gui.LoginForm;
import com.pi.tevent.Gui.MaterielAGui;


import com.pi.tevent.Gui.AddFeedbackForm;
import com.pi.tevent.Gui.AddReclamationForm;
import com.pi.tevent.Gui.HomeFeedbackForm;
import com.pi.tevent.Gui.HomeReclamationForm;
import com.pi.tevent.Gui.ListDemandeBusForm;
import com.pi.tevent.Gui.ListReclamationForm;
import com.pi.tevent.Gui.LoginForm;
import com.pi.tevent.Gui.RegisterForm;

/**
 * This file was generated by <a href="https://www.codenameone.com/">Codename One</a> for the purpose 
 * of building native mobile applications using Java.
 */
public class MyApplication   {

    private Form current;
    private Resources theme;

    public void init(Object context) {
        // use two network threads instead of one
       theme = UIManager.initFirstTheme("/theme");

        // Disable the global toolbar as we want a layered toolbar
        Toolbar.setGlobalToolbar(false);
       
    }
    
    public void start()  {
        if(current != null){
            current.show();
            return;
        }

       //   new AddDemandeBusForm(theme).show();
       // new AddDemandeChauffeurForm(theme).show();
//        new AddDemandeMaterielForm(theme).show();
// new ListDemandeBusForm(theme).show();
// new ListDemandeBusAdminForm(theme,current).show();
// new ListDemandeMaterielAdminForm(theme).show();
// new ListDemandeChauffeurAdminForm(theme).show();
//        new ListDemandeChauffeurForm(theme).show();
//        new ListDemandeMaterielForm(theme).show();

//new DemandesForm(theme).show();
//new ChartForm(theme).show();

 //new LoginForm(theme).show();
//    new LISTESSAYAGE(theme).show();
        //new LoginForm(theme).show();
        //new AddBusFrom("Add Bus", theme).show();
//        new MaterielAGui("Nos Materiel", theme).show();
      
       new AddFeedbackForm(theme).show();
    }

    public void stop() {
        current = getCurrentForm();
        if(current instanceof Dialog) {
            ((Dialog)current).dispose();
            current = getCurrentForm();
        }
    }
    
    public void destroy() {
    }

}
