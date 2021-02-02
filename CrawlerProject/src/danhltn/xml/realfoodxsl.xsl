<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output encoding="UTF-8" method="xml" omit-xml-declaration="yes"/>

    <xsl:template match="/">
        <productlist>
            <xsl:for-each select='//main[@id="phContent"]'>
                <xsl:choose>
                    <xsl:when test="position() > 0">
                        <product>
                            <imageUrl>
                                <xsl:value-of select='section[@class="recipe-detail__container recipe-detail__container_white-mob"]/div/div/div/img[contains(@class, "recipe-detail__img")]/@src'/>
                            </imageUrl>
                            <name>
                                <xsl:value-of select='section[@class="recipe-detail__container recipe-detail__container_white-mob"]/div/div/h1'/>
                            </name>
                            <time>
                                <xsl:value-of select='section[@class="recipe-detail__container recipe-detail__container_white-mob"]/div/div/ul/li/li[@class="recipe-detail__meta-item recipe-detail__meta-item_time"]'/>
                            </time>
                            <serves>
                                <xsl:value-of select='section[@class="recipe-detail__container recipe-detail__container_white-mob"]/div/div/ul/li[@class="recipe-detail__meta-item recipe-detail__meta-item_servings"]/text()'/>
                            </serves>
                            <type>
                                <xsl:value-of select='section[@class="recipe-detail__container recipe-detail__container_white-mob"]/div/div/ul/li/li[@class="recipe-detail__meta-item recipe-detail__meta-item_healthy"]'/>
                            </type>
                            <description>
                                <xsl:value-of select='section[@class="recipe-detail__container recipe-detail__container_white-mob"]/div/div[@class="recipe-detail__grid-narrow recipe-detail__grid-narrow_with-tabs"]/p/text()'/>
                            </description>
                            <ingredients>
                                <xsl:for-each select='section[@id="recipeingredients"]/div/div/ul[@class="recipe-detail__list"]/li'>
                                    <ingredient>
                                        <xsl:value-of select="text()"/>
                                    </ingredient>
                                </xsl:for-each>
                            </ingredients>
                            <steps>
                                <xsl:for-each select='section[@id="method"]/div/ol/li'>
                                    <step>
                                        <xsl:value-of select="text()"/>
                                    </step>
                                </xsl:for-each>
                                <tip>
                                   <xsl:value-of select='section[@id="method"]/div/p/text()'/> 
                                </tip>
                            </steps>
                            <cals>
                                <xsl:value-of select='section[@class="recipe-detail__container recipe-detail__container_white-mob"]/div/div/ul/li/li[@class="recipe-detail__meta-item recipe-detail__meta-item_calories"]'/>
                            </cals>
                        </product>
                    </xsl:when>
                </xsl:choose>
            </xsl:for-each>
        </productlist>
    </xsl:template>

</xsl:stylesheet>
