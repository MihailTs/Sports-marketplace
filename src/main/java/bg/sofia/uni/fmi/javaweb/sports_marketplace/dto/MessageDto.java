package bg.sofia.uni.fmi.javaweb.sports_marketplace.dto;

import java.util.UUID;

public record MessageDto (
     UUID chatId,
     String content
){}
