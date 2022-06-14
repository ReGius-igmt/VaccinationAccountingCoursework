package ru.regiuss.client.deserializer;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import ru.regiuss.client.model.Laboratory;
import ru.regiuss.client.model.Role;
import ru.regiuss.client.model.User;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserDeserializer extends StdDeserializer<User> {

    public UserDeserializer() {
        this(null);
    }

    public UserDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public User deserialize(JsonParser jp, DeserializationContext context) throws IOException, JacksonException {
        JsonNode node = jp.getCodec().readTree(jp);
        if(node == null)return null;
        User user = new User();
        user.setId(node.get("id").asInt());
        user.setFirstName(node.get("firstName").asText());
        user.setLastName(node.get("lastName").asText());
        if(!node.get("patronymic").isNull())user.setPatronymic(node.get("patronymic").asText());
        user.setSex(node.get("sex").asInt());
        user.setPhone(node.get("phone").asText());
        user.setLogin(node.get("login").asText());
        Set<String> permissions = new HashSet<>();
        Set<Role> roles = new HashSet<>();
        for(JsonNode role : node.get("roles")){
            roles.add(jp.getCodec().treeToValue(role, Role.class));
            for(JsonNode pex : role.get("permissions")){
                permissions.add(pex.get("name").asText());
            }
        }
        user.setPermissions(permissions);
        user.setRoles(roles);
        user.setLaboratories(jp.getCodec().readValue(node.get("laboratories").traverse(), new TypeReference<>() {}));
        return user;
    }
}
