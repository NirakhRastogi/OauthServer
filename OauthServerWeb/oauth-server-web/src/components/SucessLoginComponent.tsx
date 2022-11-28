import React from "react";
import {useSearchParams} from "react-router-dom";

type SuccessLoginProps = {
}


export const SucessLoginComponent: React.FunctionComponent<SuccessLoginProps> = (props: SuccessLoginProps) => {

    let [searchParams, ] = useSearchParams();
    const accessToken: string | null = searchParams.get('accessToken');

    return (
                    <div>
                        <h1>Successfully loggedin </h1>
                        <h2>{accessToken}</h2>
             </div>

    )

}