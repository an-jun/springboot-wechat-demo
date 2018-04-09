package cc.anjun.wechat.domain.res;

import cc.anjun.wechat.domain.BaseMessage;

public class VoiceMessage extends BaseMessage {

    private Voice Voice;

    public Voice getVoice() {
        return Voice;
    }

    public void setVoice(Voice voice) {
        Voice = voice;
    }


}
