package com.mcnasimba.msvc_clients.dtos.accounts;

import com.mcnasimba.msvc_clients.dtos.ClientDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class ClientStateDTO extends ClientDTO {
    List<AccountStateDTO> movements;
}
