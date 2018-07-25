package com.eyeieye.melody.demo.web.action;

import com.eyeieye.melody.demo.web.action.login.User;
import com.eyeieye.melos.util.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CommonAction {

    @RequestMapping(value = "new_feature/introduce",method = RequestMethod.GET)
    public void newFeature(ModelMap modelMap){
        modelMap.put("selected","new_feature");
    }
    @RequestMapping(value = "config/introduce",method = RequestMethod.GET)
    public void configIntro(ModelMap modelMap){
        modelMap.put("selected","config");
    }
    @RequestMapping(value = "dir_url/introduce",method = RequestMethod.GET)
    public void dirUrlIntro(ModelMap modelMap){
        modelMap.put("selected","dir_url");
    }
    @RequestMapping(value = "webpages/introduce",method = RequestMethod.GET)
    public void webpagesIntro(ModelMap modelMap){
        modelMap.put("selected","webpages");
    }
    @RequestMapping(value = "annotation/introduce",method = RequestMethod.GET)
    public void annotationIntro(ModelMap modelMap){
        modelMap.put("selected","annotation");
    }
    @RequestMapping(value = "nosession/introduce",method = RequestMethod.GET)
    public void nosessionIntro(ModelMap modelMap){
        modelMap.put("selected","nosession");
    }
    @RequestMapping(value = "utils/introduce",method = RequestMethod.GET)
    public void utilsIntro(ModelMap modelMap){
        modelMap.put("selected","utils");
    }
    @RequestMapping(value = "interceptor/introduce",method = RequestMethod.GET)
    public void interceptorIntro(ModelMap modelMap){
        modelMap.put("selected","interceptor");
    }
    @RequestMapping(value = "others/introduce",method = RequestMethod.GET)
    public void othersIntro(ModelMap modelMap){
        modelMap.put("selected","others");
    }
    @RequestMapping(value = "spring/introduce",method = RequestMethod.GET)
    public void springIntro(ModelMap modelMap){
        modelMap.put("selected","spring");
    }

    @RequestMapping("contain/header")
    public void header(User user, ModelMap modelMap){
        if(StringUtil.isEmpty(user.getUuid()) == false){
            modelMap.put("logined",true);
        }else{
            modelMap.put("logined",false);
        }
    }

}
