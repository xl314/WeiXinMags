package lpssy.service;

import cn.hutool.json.JSONUtil;
import lpssy.domain.DataInfo;
import lpssy.domain.VxMessageDto;
import lpssy.utils.DateUtil;
import lpssy.utils.VxUtil;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class MessageService {


    /**
     * {{userName.DATA}}，
     * 今天是我们在一起的{{holdDay.DATA}}天
     * 你的生日还有{{yourBirthDay.DATA}}天
     * 我的生日还有{{myBirthDay.DATA}}天
     * 距离我们的下一次纪念还有{{loveDay.DATA}}天
     * 最后，开心每一天！
     */
    //对应秒、分、时、天、月、星期。
    @Scheduled(cron = "1 * * * * ?") //可以修改成你要的时间
    //@Scheduled(cron = "0 40 07 * * *?") //可以修改成你要的时间
    public void sendMessage() {
        VxMessageDto dto = new VxMessageDto();
        dto.setTemplate_id("2aquXMC8nENKJ_cLPBQzz713Xr5WifOcfvbh4gEoHWU");  //修改成你的模板ID
        dto.setTouser("IDo80L86niaQ33VpU__N1OfTvhVm0s"); //我的
        //dto.setTouser("o80L86htd_LL5_kx4wJpRYhxz_NI"); //limin用户ID
        HashMap<String, DataInfo> map = new HashMap<>();
        setMap(map, "holdDay", DateUtil.passDay(2019, 10, 1), "#FFCCCC"); //改成你在一起的时间
        setMap(map, "yourBirthDay", DateUtil.getNextChineseBirthDay(04, 29), "#FFCCCC"); //改成她的生日
        setMap(map, "loveDay", DateUtil.getNextBirthDay(7, 8), "#FFCCCC"); //改成你在一起的时间
//        setMap(map, "ZaoAn", DateUtil.zaoan(), "#9ACD32");
//        setMap(map, "TianQi", DateUtil.tianqi(), "#6C7B8B");
        dto.setData(map);
        String message = JSONUtil.toJsonStr(dto);
        VxUtil.sendMessage(message);
    }


    /**
     * @param key   模板的每项key
     * @param value 展示的内容
     * @param color 展示的颜色
     */
    private void setMap(HashMap<String, DataInfo> map, String key, String value, String color) {
        DataInfo dataInfo = new DataInfo();
        dataInfo.setColor(color);
        dataInfo.setValue(value);
        map.put(key, dataInfo);
    }


}
