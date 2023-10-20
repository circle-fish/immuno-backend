package com.kingmed.immuno.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HeliosImagePosionInDevice {
    int slide;
    int well;
    int index;
}
