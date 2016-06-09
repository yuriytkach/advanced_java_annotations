package ua.yet.adv.java.annotation.services;

import ua.yet.adv.java.annotation.Init;
import ua.yet.adv.java.annotation.Service;

@Service(name="ThrowingService")
public class ThrowingService {

    @Init(suppressException = true)
    public void init() throws Exception {
        throw new Exception("init exception");
    }

}
