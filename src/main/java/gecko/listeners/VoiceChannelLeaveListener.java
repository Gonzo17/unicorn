package gecko.listeners;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceLeaveEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceMoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class VoiceChannelLeaveListener extends ListenerAdapter {
    @Override
    public void onGuildVoiceLeave(@NotNull GuildVoiceLeaveEvent event) {
        leaveIfNoOtherMemberInVoiceChannel(event.getChannelLeft(), event.getJDA());
    }

    @Override
    public void onGuildVoiceMove(@NotNull GuildVoiceMoveEvent event) {
        leaveIfNoOtherMemberInVoiceChannel(event.getChannelLeft(), event.getJDA());
    }

    private void leaveIfNoOtherMemberInVoiceChannel(VoiceChannel voiceChannel, JDA jda) {
        List<Member> members = voiceChannel.getMembers();
        if (members.size() == 1 && members.get(0).getIdLong() == jda.getSelfUser().getIdLong()) {
            members.get(0).getGuild().getAudioManager().closeAudioConnection();
        }
    }
}
