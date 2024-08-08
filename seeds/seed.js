(async function bootstrap() {

    const BASE_URI = "http://localhost:8080/api"

    const random = (min = 0, max = 100) => Math.floor(Math.random() * (max - min)) + min

    const random_element = (array) => array.at(random(0, array.length))

    async function get(url) {
        const response = await fetch(url)

        return response.json()
    }

    async function post(url, params) {
        const response = await fetch(url, {
            method: 'POST',
            headers: {
                'content-type': 'application/json'
            },
            body: JSON.stringify(params)
        })

        return response.json()
    }

    async function put(url, params) {
        const response = await fetch(url, {
            method: 'PUT',
            headers: {
                'content-type': 'application/json'
            },
            body: JSON.stringify(params)
        })

        return response.json()
    }

    async function update_product({ product, next_price }) {
        const endpoint = BASE_URI.concat("/product/")

        console.log({
            ...product,
            price: next_price
        })

        await put(endpoint, {
            ...product,
            price: next_price
        })
    }

    async function create_order({ user, items }) {
        const endpoint = BASE_URI.concat("/order/create")

        const request_items = items.map(
            item => ({
                productId: item.product.id,
                quantity: item.quantity
            })
        )

        await post(endpoint, {
            possibleUserId: user,
            orderItemsRequest: request_items
        })
    }

    async function get_orders() {
        const endpoint = BASE_URI.concat("/order/list")
        return await get(endpoint)
    }

    async function create_customer({ name, email, cpf }) {
        const endpoint = BASE_URI.concat("/users")
        await post(endpoint, { name, email, cpf })
    }

    async function create_product({ name, description, imagePath, price, category }) {
        const endpoint = BASE_URI.concat("/product")
        await post(endpoint, { name, description, imagePath, price, category })
    }

    async function get_products_by_category({ category }) {
        const endpoint = BASE_URI.concat("/product").concat("/").concat(category)
        return await get(endpoint)
    }

    async function get_user_by_cpf({ cpf }) {
        const endpoint = BASE_URI.concat("/users").concat("?document=").concat(cpf)
        try {
            await get(endpoint)
        } catch (error) {
            return null
        }
    }

    async function print_order(order) {
        console.log(`
            Pedido #${order.id}
            Status: ${order.status}
            Usuario: ${order.possibleUserView?.name ?? "Não Identificado"}
            Total: ${order.total},
            Items: \n${order.items.map(item => `\t\t${item.quantity}x ${item.name}`).join("\n")}
        `)
    }

    async function create_mc_donalds() {
        const responses = await Promise.allSettled(
            Array.of(
                create_customer({
                    name: 'Ronald Mcdonalds',
                    email: 'ronald@mcdonalds.com',
                    cpf: '32165498799'
                }),
                create_product({
                    name: 'Big Mac',
                    price: 19.90,
                    category: 'DISH',
                    description: 'Dois hambúrgueres (100% carne bovina), alface americana, queijo processado sabor cheddar, molho especial, cebola, picles e pão com gergelim.',
                    imagePath: 'https://cache-backend-mcd.mcdonaldscupones.com/media/image/product$kzXCTbnv/200/200/original?country=br'
                }),
                create_product({
                    name: 'Cheddar McMelt',
                    price: 23.75,
                    category: 'DISH',
                    description: 'Um hambúrguer (100% carne bovina), molho lácteo com queijo tipo cheddar, cebola ao molho shoyu e pão escuro com gergelim.',
                    imagePath: 'https://cache-backend-mcd.mcdonaldscupones.com/media/image/product$kzXv7hw4/200/200/original?country=br'
                }),
                create_product({
                    name: 'Coca-Cola 500ml',
                    price: 15,
                    category: 'DRINK',
                    description: 'Refrescante e geladinha. Uma bebida assim refresca a vida. Você pode escolher entre Coca-Cola, Coca-Cola Zero, Sprite sem Açúcar, Fanta Guaraná e Fanta Laranja.',
                    imagePath: 'https://cache-backend-mcd.mcdonaldscupones.com/media/image/product$kNXBvqQj/200/200/original?country=br'
                }),
                create_product({
                    name: 'Del Valle 700ml',
                    price: 17.50,
                    category: 'DRINK',
                    description: 'Deliciosos sabores à sua escolha. Néctar de fruta nos sabores uva ou laranja.',
                    imagePath: 'https://cache-backend-mcd.mcdonaldscupones.com/media/image/product$kNXWVFLM/200/200/original?country=br'
                }),
                create_product({
                    name: 'McFritas Grande',
                    price: 12.50,
                    category: 'SIDE_DISH',
                    description: 'A batata frita mais famosa do mundo. Deliciosas batatas selecionadas, fritas, crocantes por fora, macias por dentro.',
                    imagePath: 'https://cache-backend-mcd.mcdonaldscupones.com/media/image/product$kUXVg4F7/200/200/original?country=br'
                }),
                create_product({
                    name: 'Chicken McNuggets 10 unidades',
                    price: 18.50,
                    category: 'SIDE_DISH',
                    description: 'Crocantes, leves e deliciosos. Os irresistíveis McNuggets são feitos com carne 100% peito de frango.',
                    imagePath: 'https://cache-backend-mcd.mcdonaldscupones.com/media/image/product$kMXFAQwe/200/200/original?country=br'
                }),
                create_product({
                    name: 'McShake Ovomaltine',
                    price: 15,
                    category: 'DESSERT',
                    description: 'Deliciosamente cremoso. O novo McShake Ovomaltine é feito com leite e batido na hora. Uma delícia!',
                    imagePath: 'https://cache-backend-mcd.mcdonaldscupones.com/media/image/product$kJX0TX33/200/200/original?country=br'
                }),
                create_product({
                    name: 'McColosso Chocolate',
                    price: 5.50,
                    category: 'DESSERT',
                    description: 'Não é uma sobremesa qualquer, é um verdadeiro colosso. Uma supercasquinha de biscoito em forma de cone, recheada com bebida láctea sabor baunilha e uma sensacional cobertura sabor chocolate.',
                    imagePath: 'https://cache-backend-mcd.mcdonaldscupones.com/media/image/product$kpXUNJSU/200/200/original?country=br'
                })
            )
        )

        for (const response of responses) {
            console.log(response)
        }
    }

    async function create_random_order({ cpf }) {
        const user = cpf && await get_user_by_cpf({ cpf })
        const id = user?.id ?? null

        const dishes = await get_products_by_category({ category: 'DISH' })
        const drinks = await get_products_by_category({ category: 'DRINK' })
        const sides = await get_products_by_category({ category: "SIDE_DISH" })
        const desserts = await get_products_by_category({ category: 'DESSERT' })

        const random_dish = random_element(dishes)
        const random_drink = random_element(drinks)
        const random_side_dish = random_element(sides)
        const random_dessert = random_element(desserts)

        await create_order({
            user: id,
            items: Array.of(
                { product: random_dish, quantity: random(1, 5) },
                { product: random_drink, quantity: random(1, 3) },
                { product: random_side_dish, quantity: random(1, 3) },
                { product: random_dessert, quantity: random(1, 4) },
            )
        })
    }

    async function create_many_orders({ size }) {
        await Promise.allSettled(
            Array.from({ length: size }, () => create_random_order({ cpf: null }))
        )
    }

    const drinks = await get_products_by_category({ category: "DRINK" })

    console.log(drinks)

    const drink = drinks.at(0)

    await update_product({
        product: drink,
        next_price: 8.50
    })

    console.log(
        await get_products_by_category({ category: "DRINK" })
    )

    // const orders = await get_orders()

    // for (const order of orders) {
    //     print_order(order)
    //     console.log("-------------------------------")
    // }

})()