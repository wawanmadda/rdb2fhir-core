package org.bayisehat.rdb2fhir.core.util;

/**
 * A set of methods related to FHIR that is widely used but do not belong to any packages
 * */
public class FhirTool {

    public static StringBuilder appendSliceName(StringBuilder sb, String sliceName) {
        if (sliceName == null) {
            return sb;
        }

        return sb.append(":").append(sliceName);
    }

    public static StringBuilder appendIndex(StringBuilder sb, Integer index) {
        if (index == null) {
            return sb;
        }

        return sb.append("[").append(index).append("]");
    }

    public static StringBuilder appendName(StringBuilder sb, String name) {
        if (sb.isEmpty()) {
            return sb.append(name);
        }
        return sb.append(".").append(name);
    }

    public static StringBuilder appendType(StringBuilder sb, String dataType) {
        if (dataType == null) {
            return sb;
        }
        return sb.append("{").append(dataType).append("}");
    }

    public static StringBuilder appendElementNameForApp(StringBuilder sb, String name, String dataType, String sliceName, Integer index) {
        return appendIndex(appendSliceName(appendType(appendName(sb, name), dataType), sliceName), index);
    }

    public static StringBuilder appendTypeForFhirPath(StringBuilder sb, String dataType, boolean isResourceType) {
        if ( isResourceType) {
            return appendType(sb, dataType);
        }

        if (dataType == null) {
            return sb;
        }

        return sb.append(dataType.substring(0, 1).toUpperCase()).append(dataType.substring(1));
    }


    public static StringBuilder appendElementNameForFhir(StringBuilder sb, String name, String dataType, String sliceName, Integer index, boolean isResourceType) {
        return appendIndex(appendSliceName(appendTypeForFhirPath(appendName(sb, name), dataType, isResourceType), sliceName), index);
    }



    /**
     * Remove resource type if exist in element target
     * ex: Patient.active => active
     * ex: active => active
     * ex: Patient => '' (empty string)
     * */
    public static String removePrefixClassName(String prefix, String str) {
        if (prefix.equals(str)) {
            return "";
        }
        return str.startsWith(prefix + ".") ? str.replaceFirst(prefix + ".", ""): str;
    }


}
