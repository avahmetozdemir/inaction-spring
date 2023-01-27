FROM openjdk:19

COPY target/lawmanInaction.jar lawmanInaction.jar

ENTRYPOINT [ \
"java", \
"-jar", \
"/lawmanInaction.jar"]