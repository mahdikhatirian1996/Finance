package com.org.finance.Service.Impl.Newpairs;

import com.org.finance.Service.Dextools.IDextoolsService;
import com.org.finance.Service.Honeypot.IHoneypotService;
import com.org.finance.Service.Newpairs.INewPairsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewPairsService implements INewPairsService {

    @Autowired
    private IDextoolsService iDextoolsService;

    @Autowired
    private IHoneypotService iHoneypotService;

    @Override
    public List getAll(Integer currentPage, Integer pageSize) {
        return null;
    }
}
