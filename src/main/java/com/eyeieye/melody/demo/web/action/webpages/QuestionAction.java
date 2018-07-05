package com.eyeieye.melody.demo.web.action.webpages;


import com.eyeieye.melody.demo.domain.DemoHero;
import com.eyeieye.melody.demo.service.impl.SimpleCacheManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.Random;

@Controller
@RequestMapping("/webpages/demo")
public class QuestionAction {

    SimpleCacheManager cacheManager = new SimpleCacheManager();

    @RequestMapping(value = "quetionStart.htm", method = RequestMethod.GET)
    public String questionStartPage(Integer level,  ModelMap modelMap) {
        return "/webpages/demo/questionStart";
    }

    @RequestMapping(value = "quetionStart.htm", method = RequestMethod.POST)
    public String questionStart(Integer level,  ModelMap modelMap) {
        if (level == null) {
            modelMap.put("id", null);
        } else {
            DemoHero hero = generateNewHero(level);
            cacheManager.put(String.valueOf(hero.getId()), hero);
            modelMap.put("id", hero.getId());
        }


        return "/webpages/demo/questionExecute";
    }

    @RequestMapping(value = "questionResult.htm")
    public void result(HttpServletRequest request, ModelMap modelMap) throws Exception{
        Integer sleep =(Integer) request.getAttribute("sleep");
        Thread.sleep(sleep);
        Long heroId =(Long) request.getAttribute("id");
        //Long heroId = Long.valueOf(id);
        if(heroId==null){
            modelMap.put("hero", null);
            return;
        }
        Object o = cacheManager.get(String.valueOf(heroId));
        if(o != null) {
            DemoHero hero = (DemoHero)o;
            if(hero.getHealthPoint() <= 0 && hero.getFloor()>1){
                modelMap.put("hero", null);
                return;
            }
            Random r = new Random();
            Integer hit = r.nextInt(100)+100;
            Integer heart = (hit - hero.getLevel())>0?(hit - hero.getLevel()):0;
            hero.setHealthPoint(hero.getHealthPoint() - heart);
            hero.setFloor(hero.getFloor()+1);
            modelMap.put("hero",hero);
        }else{
            modelMap.put("hero", null);
        }
    }


    synchronized private DemoHero generateNewHero(Integer level) {
        DemoHero demoHero = new DemoHero();
        demoHero.setLevel(level);
        demoHero.setId(DemoHero.getNextId());
        return demoHero;
    }
}